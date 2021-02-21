# Manorrock Korat

This project delivers a DNS server.

## Deploy the server using Docker

```
  docker run --privileged --rm -d -p 8080:8080 -p 53:53/tcp -p 53:53/udp manorrock/korat:VERSION
```

And replace VERSION with the version you want to use.\

## Important notice

Note if you file issues or answer questions on the issue tracker and/or issue 
pull requests you agree that those contributions will be owned by Manorrock.com
and that Manorrock.com can use those contributions in any manner Manorrock.com
so desires.
