set SELENIUM_SERVER_PATH=C:\devtools\lib\selenium-server-standalone\selenium-server-standalone-3.8.1.jar
set CHROME_DRIVER_EXECUTABLE_PATH=C:\devtools\drivers\chrome\2.34\chromedriver_win32\chromedriver.exe
start java -Dwebdriver.chrome.driver=%CHROME_DRIVER_EXECUTABLE_PATH% -jar %SELENIUM_SERVER_PATH% -role hub
start java -Dwebdriver.chrome.driver=%CHROME_DRIVER_EXECUTABLE_PATH% -jar %SELENIUM_SERVER_PATH% -role node -hub http://localhost:4444/grid/register
