from pymysql import *
import os
###Config Values
endpoint = 'database-1.cmdytnitoh1c.us-east-1.rds.amazonaws.com'
username = 'admin'
password = 'Momanddad-1'
database_name = 'Borchelt_Test_Server'
table_name = 'TN'

#connection etilizing the mysql library

connection = connect(endpoint,user=username,passwd=password,db=database_name,connect_timeout=200)

def pushInfo(a,g,la,lo,p):
    cursor = connection.cursor()
    sql = "insert into NT (accuracy, gpsAltitude, latitude, longitude, pressure) values({0},{1},{2},{3},{4})".format(a,g,la,lo,p)
    cursor.execute(sql)
    connection.commit()
    
#def pullInfo():
    
    
def pushInfoFromFile(fileToImport):
    cursor = connection.cursor()
    filename = os.path.join(os.path.dirname(os.path.realpath(__file__)),fileToImport)
    with open(filename,'r') as data:
        data = data.readlines()
        for l in data[1:]:
            a,g,la,lo,p = l.strip().split('\t')
            pushInfo(a,g,la,lo,p)
        print('finished pushing')
    
pushInfoFromFile('SN9data.txt')
