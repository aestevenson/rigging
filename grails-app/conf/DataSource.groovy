import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration   
dataSource {
    pooled = false
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "castaway"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
cache.use_second_level_cache=true
cache.use_query_cache=true
cache.provider_class='org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
environments {
	development {
   		dataSource {
   			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
   			url = "jdbc:mysql://localhost/rigging_dev"
   		}
	}
	test {
		dataSource {
   			dbCreate = "update" // one of 'create', 'create-drop','update'
   	   		url = "jdbc:mysql://localhost/rigging_dev"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}