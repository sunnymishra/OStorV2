# OStorV2

This is a Maven 3.x based Java web project exposing some Jax-rs based Restful APIs.
These APIs internally call Dropbox Restful API version 2.0

To initiate, download this project and do a Maven build:
> mvn clean install

Once the WAR file is created:
- Start the server
- To call Dropbox APIs you need to get the Dropbox Token. For that hit following URL in browser:
    > http://localhost:8080/OStorProjectV2/
- Once Authorized from Dropbox, copy the access_token and call any OStorV2 API by providing token in Request Header:

Example:
Try below Example CLient request from any Rest client, eg.: Postman Chrome plugin, DHC Chrome plugin
> URL: http://localhost:8080/OStorProjectV2/api/file/list_folder
> METHOD: POST
> header "Authorization: Bearer <get access token>" \
> header "Content-Type: application/json" \
> data "{\"path\": \"/Notes\",\"recursive\": false,\"include_media_info\": false,\"include_deleted\": false,\"include_has_explicit_shared_members\": false}"
