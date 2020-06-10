# Manorrock Korat

This project delivers a Java based DNS server.

## Deploy the server using Docker

```
  docker run --privileged --rm -d -p 53:53/tcp -p 53:53/udp manorrock/korat:VERSION
```

And replace VERSION with the version you want to use.

## Zone file format

The zone files are formatted compliant to the following:

1. [RFC 1035](http://tools.ietf.org/html/rfc1035) 
1. [RFC 1034](http://tools.ietf.org/html/rfc1034)
