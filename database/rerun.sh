#### Build Database
sudo docker build ./ -t bpk_db
echo "Database built successfully"
#### Remove previous Database container
./rm.sh
#### Run Database
sudo docker run --name bpk_db -d -p ${BP_KEEPER_PORT}:5432 bpk_db
