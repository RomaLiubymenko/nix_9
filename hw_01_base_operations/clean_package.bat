call chcp 65001
call cd ../common_ui && call clean_install.bat
call cd ../hw_01_base_operations && call mvn clean package
