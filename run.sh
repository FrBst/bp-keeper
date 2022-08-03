cd database
./rerun.sh

cd ..
./wait-for-it.sh localhost:5432 --strict -- echo "Database ready"

cd backend
#### Build Backend
sudo docker build ./ -t bpk_backend --network=host
echo "Backend built successfully"
#### Remove previous Backend container
sudo docker rm bpk_backend

cd ../database
#### Stop and remove Database container
./rm.sh

cd ..
#### Run all the containers!
sudo docker-compose up
