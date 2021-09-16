#!/bin/bash
docker run -v $(pwd):/var/www/html -p 8001:80 symfony
