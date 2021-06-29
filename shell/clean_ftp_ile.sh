#!/bin/sh
dest_dir=/data  #导出文件所放目录
log_path=/data/data.log  #日志文件目录

ftp_ip=192.168.100.2
ftp_username=work
ftp_password="xxxxx"
ftp_path=/mnt/ftp/data
ftp_port=21

function clean_old_file(){
#0_DAY_000_20210107.CHK
local curtime
curtime=`date +"%F %T"`
echo "清除堆积文件开始|${curtime}"

for file_pwd in $(find $dest_dir -name "NG3_PAAS_*" -type f);
do
    file_size=$(ls -lh $file_pwd | awk '{print $5}')
    echo `date "+%Y-%m-%d %H:%M:%S"` "ftp delete start"
ftp -n << EOF
open $ftp_ip
user $ftp_username $ftp_password
passive
binary
cd $ftp_path
delete $file_pwd
quit
EOF
    echo `date "+%Y-%m-%d %H:%M:%S"` "ftp delete end"
 if [ ! -f "$file_pwd" ]; then
     rm_date=`date +"%F %T"`
     echo "$rm_date | $file_pwd | $file_size 已删除"
 else
    rm_date=`date +"%F %T"`
    echo "$rm_date | $file_pwd | $file_size 删除失败 "
 fi
done

curtime=`date +"%F %T"`
echo "清除堆积文件结束|${curtime}"
}
clean_old_file