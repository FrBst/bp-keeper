FROM postgres:14.3
COPY sql/*.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_DB bpk
