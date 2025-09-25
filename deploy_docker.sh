


## You need to be logged in with your user, otherwise run `docker login`
USER=k8sdemos
VERS=${VERS:-24.7}

## Build the application for control-center in production mode
mvn clean package -DskipTests -P production -P control-center
docker build --tag $USER/bakery-cc:$VERS .
docker push $USER/bakery-cc:$VERS


## Build the application for control-center in production mode
mvn clean package -DskipTests -P production
docker build --tag $USER/bakery:$VERS .
docker push $USER/bakery:$VERS
