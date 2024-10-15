## Usage

1. Build jar `./mvnw clean package`
2. Copy to keycloak providers
3. Rebuild providers with `./bin/kc.sh build`
4. Start keycloak `./bin/kc.sh start`
5. Check endpoint `/realms/<realm>/check-password/<login>?password=<chek-string>`