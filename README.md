# Manorrock Korat

This project delivers a Java based DNS server.

## Deploy the server using Docker

```
  docker run --privileged --rm -d -p 8080:8080 -p 53:53/tcp -p 53:53/udp manorrock/korat:VERSION
```

And replace VERSION with the version you want to use.

## Zone file format

The zone files are formatted compliant to the following:

1. [RFC 1035](http://tools.ietf.org/html/rfc1035) 
1. [RFC 1034](http://tools.ietf.org/html/rfc1034)

## Important notice

Note if you file issues or answer questions on the issue tracker and/or issue 
pull requests you agree that those contributions will be owned by Manorrock.com
and that Manorrock.com can use those contributions in any manner Manorrock.com
so desires.
