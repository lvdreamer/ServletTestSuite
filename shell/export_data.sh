#!/bin/sh
dest_dir=/data  #导出文件所放目录
log_path=/data/data.log  #日志文件目录
 
cur_date=`date "+%Y%m%d"`
cur_time=`date "+%Y%m%d%H%M%S"`  #定义当前时间变量，时间格式例如2018-12-13
back_time=$((3*24*60*60))  #定义3天秒数（这个是为了删除3天前生成的文件，只保留近三天产出的文件）
 
source_mysql_host="127.0.0.1"  #数据库地址
source_mysql_database="db"  #数据库名称
source_mysql_user="root"  #用户名
source_mysql_password="123456"  #用户名密码
source_mysql_port=3306   #数据库端口号
ftp_ip=127.0.0.2
ftp_username=ftp
ftp_password="123456"
ftp_path=/home/ftp/temp
ftp_port=21 
 
function export_table(){   #定义导出文件函数
TABLE_NAME=$1
FILE_NAME=a_table_$1_
echo $TABLE_NAME $FILE_NAME        
 
echo `date "+%Y-%m-%d %H:%M:%S"` "Start export  table $TABLE_NAME"
        
    echo `date "+%Y-%m-%d %H:%M:%S"` "start dump $source_mysql_database to $dest_dir/filename.$back_date " >> $log_path
         mysql -N --default-character-set=utf8 -D$source_mysql_database -u$source_mysql_user -p$source_mysql_password -P$source_mysql_port -h$source_mysql_host -e "select * from $TABLE_NAME" |sed -e  's/\t/\x01/g' -e 's/NULL//g' -e 's/\n/\r\n/g'  > $dest_dir/$FILE_NAME$cur_time.data    #将table_name1表数据导出filename.$back_date文件中         
if [ "$?" == "0" ] && [ -s $dest_dir/$FILE_NAME$cur_time.data ]; then #$?:执行上一条指令的返回值 0表示没有错误
	echo `date "+%Y-%m-%d %H:%M:%S"` "export complete"   #将导出完成时间记录到日志文件里
else
    echo `date "+%Y-%m-%d %H:%M:%S"` "export table error"
    rm $dest_dir/$FILE_NAME$cur_time.data
    exit
fi
        echo -e "$FILE_NAME$cur_time.data\x01$(cat $dest_dir/$FILE_NAME$cur_time.data | wc -l)\x01$cur_date" > $dest_dir/$FILE_NAME$cur_time.chk  
        echo `date "+%Y-%m-%d %H:%M:%S"` "make check file  End"   #
 
echo `date "+%Y-%m-%d %H:%M:%S"` "ftp upload start"
ftp -n << EOF
open $ftp_ip
user $ftp_username $ftp_password
passive
binary
cd $ftp_path
lcd $dest_dir
put $FILE_NAME$cur_time.data
put $FILE_NAME$cur_time.chk
quit
EOF
echo `date "+%Y-%m-%d %H:%M:%S"` "ftp upload end"
}

function clean_old_file(){
for file_pwd in $(find $dest_dir -name "a_table*" -type f -mtime -3);
do
 file_size=$(du -sh $file_pwd | awk '{print $1}')
 #rm -f $file_pwd >/dev/null 2>&1
 if [ ! -f "$file_pwd" ]; then
	 rm_date=`date +"%F %T"`
	 echo "$rm_date | $file_pwd | $file_size 已删除"  
 else 
    rm_date=`date +"%F %T"`
	echo "$rm_date | $file_pwd | $file_size 删除失败 "
 fi
done
}


export_table table1
export_table table2
clean_old_file
