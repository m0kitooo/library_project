@echo off
REM This script runs a Docker container with the specified image and options.

docker build -t my-sql-image .

docker run --init --rm -d ^
  --name my-sql-container ^
  -v library_project:/var/lib/mysql ^
  -p 3307:3307 ^
  my-sql-image