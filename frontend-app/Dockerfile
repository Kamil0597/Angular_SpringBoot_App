FROM nginx:1.25

LABEL org.opencontainers.image.title="simple-rpg-ng"
LABEL org.opencontainers.image.authors="Twoje Imię"
LABEL org.opencontainers.image.description="Simple RPG game, Angular frontend"
LABEL org.opencontainers.image.version="1.0.0-SNAPSHOT"

ENV API_URL=http://host.docker.internal:8082/api

# Eksport portu 80
EXPOSE 80

# Kopiowanie plików aplikacji Angular do katalogu NGINX
ADD dist/frontend-app/browser /usr/share/nginx/html/

# Kopiowanie konfiguracji NGINX
ADD default.conf.template /etc/nginx/templates/default.conf.template

# Instalacja narzędzia envsubst
RUN apt-get update && apt-get install -y gettext-base && rm -rf /var/lib/apt/lists/*

CMD ["sh", "-c", "envsubst '${API_URL}' < /etc/nginx/templates/default.conf.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"]
