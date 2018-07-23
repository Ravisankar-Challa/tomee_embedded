package com.example.util;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Provider
public class GZIPDecoder implements ReaderInterceptor {
   public Object aroundReadFrom(ReaderInterceptorContext ctx)
                                throws IOException {
      String encoding = ctx.getHeaders().getFirst("Content-Encoding");
      if (encoding == null || !encoding.toLowerCase().contains("gzip")) {
         return ctx.proceed();
      }
      ctx.setInputStream(new GZIPInputStream(ctx.getInputStream()));
      return ctx.proceed();
   }
}