#!/bin/bash
docker run -e APP_ENV=prod -e APP_DEBUG=0 -v $(pwd):/var/www/html -p 8001:80 symfony
