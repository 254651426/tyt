function FindProxyForURL(url, host) {
	<#list paclist as su>
    if (host == '${su}' || dnsDomainIs(host, '.${su}')) {
        return 'SOCKS5 127.0.0.1:"+CommonConstant.localPort+"';
    }
    </#list>
  return 'DIRECT;';
}