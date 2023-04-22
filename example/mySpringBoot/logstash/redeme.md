logstash-cloud-project.conf
````
input {
  tcp {
    mode => "server"
    host => "0.0.0.0"
    port => 5044
    codec => json_lines
  }
}
output {
  elasticsearch {
    # es 连接地址
    hosts => ["https://172.18.0.2:9200"]
    # 索引名称
    index => "app-logstash-%{+YYYY.MM.dd}"
    user => "elastic"
	password => "elastic123"
    ssl => "true"
	cacert => "/usr/share/logstash/config/certs/http_ca.crt"
  }
}
````