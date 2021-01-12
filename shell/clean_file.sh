#!/bin/sh
dest_dir=/data/
log_path=/data/data-clean.log
function clean_old_file() {
	local curtime
	curtime=$(date +"%F %T")
	echo "清除堆积文件开始|$curtime"
	last_file=$(ls -r *_000_*.CHK | head -1)
	ES=$?
	if [ "$ES" -ne 0 ]; then
		echo "没有发现全量文件"
		exit $ES
	fi
	if [ ! -n "$last_file" ]; then
		echo "没有发现全量文件,退出"
		exit
	fi
	echo "发现最新全量文件名:$last_file"
	last_day=${last_file:0-15:8}
	echo "发现最新全量文件日期:$last_day"
	echo "开始清理最新日期两天前的文件"
	for i in {1..7}; do
		last_lx_day=$(date -d "$last_day -${i}days" "+%Y%m%d")
		echo "清理$last_lx_day日文件"
		for file_pwd in $(find $dest_dir -name "0_DAY_*${last_lx_day}_*" -type f); do
			file_size=$(du -sh $file_pwd | awk '{print $1}')
			rm -f $file_pwd >/dev/null 2>&1
			if [ ! -f "$file_pwd" ]; then
				rm_date=$(date +"%F %T")
				echo "$rm_date | $file_pwd | $file_size 已删除"
			else
				rm_date=$(date +"%F %T")
				echo "$rm_date | $file_pwd | $file_size 删除失败 "
			fi
		done
	done
	curtime=$(date +"%F %T")
	echo "清除堆积文件结束|$curtime"
}
clean_old_file
