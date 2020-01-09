@echo off
if not exist ..\python_out mkdir ..\python_out
pip install grpcio-tools
python -m grpc_tools.protoc ../../proto/connector_service.proto --grpc_python_out=../python_out --python_out=../python_out -I../../proto
python -m grpc_tools.protoc ../../proto/wrappers.proto --python_out=../python_out -I../../proto
python -m grpc_tools.protoc ../../proto/empty.proto --python_out=../python_out -I../../proto