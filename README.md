# encode-base64

## Description
The purpose of this ms is encode string in base64

## APIs description
```java
   encode(EncodeBase64Request request)
```
This API is used to encode plain text in base64.

   1.  Receives the string plainText
   2.  Encode the plain text with the encode type
   3.  Return the encode string with 200 http code
   
### Versioning

- 0.0.1-SNAPSHOT

#### Important Changes

- **Changes**
- initial-commit
- added README.md



### Endpoints

>/api/encode

### Request & Response examples

- URL : /api/encode
- Method : POST

```json
Test data :

> Request sample:
{
    "encodeType" : "base64",
    "textPlain" : "textPlain"
}


> Response sample: 
{
    "textEncoded": "dGV4dFBsYWlu"
}

```
