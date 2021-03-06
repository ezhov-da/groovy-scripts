import groovy.sql.Sql

def url = 'jdbc:sqlserver://;servername=OTZ-prod1;integratedSecurity=true'
def user = 'login'
def password = 'pass'
def driver = 'com.microsoft.sqlserver.jdbc.SQLServerDriver'
def sql = Sql.newInstance(url, user, password, driver)

def query = 'select uniqueID, "WTF" from OTZ.dbo.T_E_test_Servicesheet'

sql.query(query) { resultSet ->
  while (resultSet.next()) {
    def first = resultSet.getString(1)
    def second = resultSet.getString('WTF')
    if (second =~'WTF'){
        println "${first} -> ${second}"
    }
  }
}

sql.close()