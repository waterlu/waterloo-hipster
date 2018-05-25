{
    "packageInfo": {
        "projectPath": "${projectPath}",
        "basePackage": "${basePackage}",
        "controllerPackage": "${controllerPackage}",
        "servicePackage": "${servicePackage}",
        "daoPackage": "${daoPackage}",
        "entityPackage": "${entityPackage}",
        "dtoPackage": "${dtoPackage}",
        "voPackage": "${voPackage}",
        "author": "${author}",
        "javaPath": "src/main/java",
        "resourcePath": "src/main/resources",
        "testPath": "src/test/java"
    },
    "databaseInfo": {
        "dbType": "MySQL",
        "dbIP": "${dbIP}",
        "dbPort": "${dbPort}",
        "dbName": "${dbName}",
        "dbUsername": "${dbUsername}",
        "dbPassword": "${dbPassword}"
    },
    "tables": [
        <#list ${tableInfoList} as tableInfo>
        {

        },
        </#list>
    ]
}