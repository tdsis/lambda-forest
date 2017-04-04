# lambda-forest
*A set of tools to ease REST AWS Lambda functions*

## About Lambda Forest
Lambda Forest is a microframework that provides a set of tools to make it easier to develop rest api's using the aws lambda function and api gateway. Lambda Forest attempts to make the develpment faster by easing common tasks, such as:

 - Exception handling
 - Request body deserialization
 - Response body serialization
 - Request validation
 
## Before you begin

In order to use Lambda Forest the AWS API Gateway needs to be configured to use Proxy Integration.

A detailed documentation can be found [here](http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html).


## Handling Requests

The Lambda Forest framework provides an abstract base class called AbstratRequestHandler that handles a Lambda execution call and perform some operations such as request body deserialization, request validation, response body serialization and exception handling.

### POST / PUT / PATCH
```java
public class PostHandler extends AbstractRequestHandler<UserRequest, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(UserRequest input, Context context) throws HttpException {
        UserResponse response = new UserResponse();
        response.setId(UUID.randomUUID().toString());
        response.setName(input.getName());
        response.setAddress(input.getAddress());
        
        return response;
    }
}
```

### GET
```java
public class GetHandler extends AbstractRequestHandler<Void, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(Void input, Context context) throws HttpException {
        Optional<String> optional = getQueryStringParameter("name");
        String name = optional.orElseThrow(() -> new UnprocessableEntityException());
    
        UserResponse response = new UserResponse();
        response.setId(UUID.randomUUID().toString());
        response.setName(name);
        
        return response;
    }
}
```

You can also specify a pojo as an input of a GET request to be deserialized based on the query string parameters:

```sh
curl http://my-aws-api-gateway-resource/users?name=myname&address=myaddress
```

```java
public class GetRequest {
    
    private String name;
    private String address;
    
    ...getters and setters
}
```

```java
public class GetHandler extends AbstractRequestHandler<GetRequest, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(GetRequest input, Context context) throws HttpException {	
        UserResponse response = new UserResponse();
        response.setId(UUID.randomUUID().toString());
        response.setName(input.getName());
        response.setName(input.getAddress());
        
        return response;
    }
}
```

### DELETE
```java
public class DeleteHandler extends AbstractRequestHandler<Void, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(Void input, Context context) throws HttpException {   
        Optional<String> optional = getPathParameter("id");
        String id = optional.get();
        
        // some delete operation...
    
        UserResponse response = new UserResponse();        
        return response;
    }
}
```

## The Request / Response Attributes
The AbstractRequestHandler class provides methods to access all the request / response attributes and the Lambda environment context as well:

 Attribute                           |                 Method
 -------------------------------- | -----------------------------------------------------
 Request Headers          | getHeader("headerName")
 Response Headers     | addResponseHeader("headerName")
 Raw Request Body     | getRawRequestBody()
 Http Method              | getHttpMethod()
 Resource Path        | getPath()
 Stage Variables          | getStageVariable("stageVariableName")
 Path Parameters          | getPathParameter("parameterName")
 Query String Parameters | getQueryStringParameter("parameterName")
 
## Request Validation
Lambda Forest uses the Hibernate bean validation implementation to validate requests.
To validate the incoming request the input parameter of the *execute* method must be annotated with *@Valid*

Eg.:
```java
public class UserRequest {
    
    @Size(min=1, max=50, message="Invalid name message")
    private String name;
    
    @NotBlank(message="Invalid address message")
    private String address;

    ... getters and setters
}
```

```java
public class LambdaHandler extends AbstractRequestHandler<UserRequest, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(@Valid UserRequest input, Context context) throws HttpException {
        UserResponse response = new UserResponse();
        response.setId(UUID.randomUUID().toString());
        response.setName(input.getName());
        response.setAddress(input.getAddress());
        
        return response;
    }
}
```
If any constraint violation occurs an UnprocessableEntityException will be thrown and the http response will be serialized with the error details.


Eg.:
```sh
curl -X POST http://my-api-gateway-resource/users -d '{"name": "my name", "address": ""}'
```
I this particular example the server will respond the above request with a http status code 422 and the following response body:
```json
{
    "message": "Unprocessable entity",
    "errors": [
        {
            "attribute": "address",
            "message": "Invalid address message"
        }
    ]
}
```
A detailed documentation of the Hibernate validation can be found [here](http://hibernate.org/validator/).


If you want to provide your own bean validation mechanism, the method *resolveRequestValidator* should be overridden:

```java
@Override
protected RequestValidator resolveRequestValidator() {
    return new MyCustomRequestValidator();
}
```

## Serialization and Deserialization
Lambda Forest uses [Jackson](https://github.com/FasterXML/jackson) to serialize and deserialize the request and response body.

The deserialization and serialization strategies are based on two http headers:

 - **Content-Type** for deserialization
 - **Accept** for serialization

If you want to provide your own request body deserializer, the method *resolveDeserializerStrategy* should be overridden:
```java
@Override
protected RequestBodyDeserializerStrategy resolveDeserializerStrategy(String contentType) {
    return new MyCustomRequestBodyDeserializer();
}
```

If you want to provide your own response body serializer, the method *resolveSerializerStrategy* should be overridden:
```java
@Override
protected ResponseBodySerializerStrategy resolveSerializerStrategy(String accept) {
    return new MyCustomResponseBodySerializer();
}
```

## Running locally
The Lambda Forest framework provides a class that simulates a Lambda execution call.

To simulate a Lambda execution call it is necessary to define a execution specification in your project resource folder.

Eg.:

```
project
└───src
│   └───main
│      └───resources
│            lambda-spec.json
```

```json
{
    "context": {
        "awsRequestId": "",
        "logGroupName": "",
        "logStreamName": "",
        "functionName": "",
        "functionVersion": "",
        "invokedFunctionArn": "",
        "identity": null,
        "clientContext": {
            "client": {
                "installationId": "",
                "appTitle": "",
                "appVersionName": "",
                "appVersionCode": "",
                "appPackageName": ""
            },
            "custom": {
                
            },
            "environment": {
                
            }
        },
        "remainingTimeInMillis": 30,
        "memoryLimitInMB": 128,
        "logger": null
    },
    "request":{
            "path": "/users",
            "pathParameters": {
                
            },
            "queryStringParameters": {
                
            },
            "resource": "users",
            "stageVariables": {
            
            },
            "method":"POST",
            "headers": {
                
            },
            "body": {               
                "name": "my name",
                "message": "This is my message"
            }
      }
}
```

```java
public class LambdaHandler extends AbstractRequestHandler<UserRequest, UserResponse> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public UserResponse execute(@Valid UserRequest input, Context context) throws HttpException {
        UserResponse response = new UserResponse();
        response.setId(UUID.randomUUID().toString());
        response.setName(input.getName());
        response.setAddress(input.getAddress());
        
        return response;
    }
    
    public static void main(String [] args) {
        LambdaRunner.run("lambda-spec.json", LambdaHandler.class, args);
    }
}
```

## License
The Lambda Forest framework is open-source software licensed under the [MIT license](https://opensource.org/licenses/MIT) .
