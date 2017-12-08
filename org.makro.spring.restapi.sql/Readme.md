To run a mysql-docker container in aws (e.g.)

sudo docker run -d --name=mysql -v /data/mysql:/var/lib/mysql --env="MYSQL_ROOT_PASSWORD=<password>" -p 443:3306 mysql
