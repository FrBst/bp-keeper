### Blood Pressure Keeper Master Database

#### Build:
docker build ./ -t bpk-db

#### Run:
docker run -p 5432:5432 bpk-db
