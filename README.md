# OpenMRS Files Module

Files module facilitates upload/download files into OpenMRS database.

## File 

The File entity is meant to store binary data of an arbitrary file.

### REST

Regular OpenMRS REST interface is implemented for the File entity:

#### Get all files
`GET /openmrs/ws/rest/v1/file?v=full`

#### Get single file
`GET /openmrs/ws/rest/v1/file/{uuid}?v=full`

#### Delete a file
`DELETE /openmrs/ws/rest/v1/file/{uuid}`

#### Download file content
Special endpoint is made for downloading binary data of the file. 
The URL is included in the Full representation of File entity.

`GET /openmrs/ws/rest/v1/file/{uuid}/bytes`

#### Create file with content
Special endpoint is made to upload the file.

`POST /openmrs/ws/rest/v1/file`
```
multipart/form-data
    fileCategory: uuid
    filename: string
    mimeType: string (optional)
    file: File
```

## File Category

The File Category entity is meant as simple classification of Files in the system, free to be defined by implementation.

#### REST

The regular OpenMRS REST interface is implemented.
