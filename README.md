zeta
====

Connector for test services

Core of the project is the proto file for the GRPC service:

*./proto/connector_service.proto* and its dependency proto files

Java client
-----------

Standalone JAR for the client API can be found at
./java/bin/zeta-connector-api-1.0.0.jar. The file bundles all the dependency
libraries as well. The library can imported to a java project, but can also be
executed as such for testing the connection to the Zeta service, i.e:

java –jar zeta-connector-api-1.0.0.jar localhost:50051

*./java/src/com/profilence/*zeta -directory: Connector.java is the main
class/file; otherwise the directory contains mostly protoc-generated code. The
project files are currently for eclipse/ant

For proto-code generation, run *./proto_compile/[win-x86_64\|linux\|mac]/
java_compile.[bat\|sh]*
