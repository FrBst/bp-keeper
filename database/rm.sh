#### Stop and remove Database container
sudo docker ps -aq --filter "name=bpk_db" | grep -q . && sudo docker stop bpk_db && sudo docker rm -fv bpk_db
