
### Prerequisites

* Docker
* Java 17+

### Request example
```shell
curl -X POST --location "http://localhost:8080/change" \
    -H "Content-Type: application/json" \
    -d '{
          "pence": 12,
          "id": "9c9db188-42ed-4f87-b94c-f094bb98fd03"
        }'
```