@echo off
if not exist ..\java_out mkdir ..\java_out
.\protoc.exe ../../proto/*.proto --grpc-java_out=../java_out --plugin=protoc-gen-grpc-java=.\protoc-gen-grpc-java-1.26.0-windows-x86_64.exe --java_out=../java_out --proto_path=../../proto