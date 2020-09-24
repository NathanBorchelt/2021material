from pymysql import *
import os
###Config Values
endpoint = 'database-1.cmdytnitoh1c.us-east-1.rds.amazonaws.com'
username = 'admin'
password = 'Momanddad-1'
database_name = 'inventoryFile'
table_name = 'invTbl'

#connection etilizing the mysql library

connection = connect(endpoint,user=username,passwd=password,db=database_name,connect_timeout=200)

def pushInfo(a,g,la,lo,p):
    cursor = connection.cursor()
    sql = "insert into NT (accuracy, gpsAltitude, latitude, longitude, pressure) values({0},{1},{2},{3},{4})".format(a,g,la,lo,p)
    cursor.execute(sql)
    connection.commit()
    
def pullInfo():
    cursor = connection.cursor()
    outputPath = os.path.dirname(os.path.realpath(__file__))
    inputPath = outputPath
    
    cursor.execute('SELECT * from NT')
    rows = cursor.fetchall()
    outputFile = os.path.join(os.path.dirname(inputPath),outputPath,'SurverPull.txt')

    with open(outputFile,'w+') as file:
        file.write('accuracy, gpsAltitude, latitude, longitude, pressure\n')
        for r in rows:
            a=r[0]
            g=r[1]
            la=r[2]
            lo=r[3]
            p=r[4]
            file.write('{0},{1},{2},{3},{4}\n'.format(a,g,la,lo,p))
        print("finished writing the output file")
    
def pushInfoFromFile(fileToImport):
    cursor = connection.cursor()
    filename = os.path.join(os.path.dirname(os.path.realpath(__file__)),fileToImport)
    with open(filename,'r') as data:
        data = data.readlines()
        for l in data[1:]:
            a,g,la,lo,p = l.strip().split('\t')
            pushInfo(a,g,la,lo,p)
        print('finished pushing')
    
#pushInfoFromFile('SN9data.txt')
#pullInfo()


def invPushInfo(id1,snk,qnt,cst,url1,fType):
    cursor = connection.cursor()
    sql = 'insert into invTbl (id1, snack, quanity, cost, url1, foodType) values ({0},"{1}",{2},{3},"{4}","{5}")'.format(id1,snk,qnt,cst,url1,fType)
    print(sql)
    cursor.execute(sql)
    connection.commit()

def invPushInfoFromFile(fileToImport):
    cursor = connection.cursor()
    filename = os.path.join(os.path.dirname(os.path.realpath(__file__)),fileToImport)
    with open(filename,'r') as data:
        data = data.readlines()
        for l in range(1,len(data)):
            u1,name,url1,fType,cost = data[l].strip().split('\t')
            invPushInfo(int(l),name,0,float(cost),url1,fType)
        print('finished pushing')
        
def invPullInfo():
    cursor = connection.cursor()
    outputPath = os.path.dirname(os.path.realpath(__file__))
    inputPath = outputPath
    
    cursor.execute('SELECT * from invTbl')
    rows = cursor.fetchall()
    outputFile = os.path.join(os.path.dirname(inputPath),outputPath,'SnackSurverPull.txt')

    with open(outputFile,'w+') as file:
        file.write('id, snack, quanity, cost, url1, foodType\n')
        for r in rows:
            id1 = r[0]
            snk = r[1]
            qnt = r[2]
            cst = r[3]
            url1 = r[4]
            fType = r[5]
            file.write('{0}\t{1}\t{2}\t{3}\t{4}\n'.format(id1,snk,qnt,cst,url1,fType))
        print("finished writing the output file")
        
      
#invPushInfoFromFile('SnackInformation.txt')
invPullInfo()