FROM php:8.0.10-apache

RUN apt update && apt install -y wget git

RUN git config --global user.email "symfony@docker.com"

RUN curl -sS https://getcomposer.org/installer | tee composer-setup.php \
    && php composer-setup.php \ 
    && rm composer-setup.php* \
    && chmod +x composer.phar && mv composer.phar /usr/bin/composer

ARG SYMFONY_CLI_VERSION=4.26.3

RUN wget https://github.com/symfony/cli/releases/download/v$SYMFONY_CLI_VERSION/symfony_linux_amd64.gz \
    && gzip -d symfony_linux_amd64.gz \
    && mv symfony_linux_amd64 symfony \
    && chmod +x symfony \
    && mv symfony /usr/local/bin/

COPY docker/apache.conf /etc/apache2/sites-enabled/000-default.conf

