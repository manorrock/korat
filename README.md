# Manorrock Korat

This project delivers a Java based DNS server.

## Deploy the server using Docker

```
  docker run --privileged --rm -d -p 53:53/tcp -p 53:53/udp manorrock/korat:VERSION
```

And replace VERSION with the version you want to use.
