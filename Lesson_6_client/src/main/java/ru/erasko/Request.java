package ru.erasko;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Request {

    private final StringBuilder requestString;

    private final String method;
    private final String path;
    private final String host;
    private final Object requestBody;

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHost() {
        return host;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public static class Builder {
        private final StringBuilder requestBuilder = new StringBuilder("");
        private String method;
        private String path;
        private String host;
        private Object requestBody;

        public Builder() {
        }

        public Builder setMethod(String method) {
            this.method = method;
            requestBuilder.append(method);
            requestBuilder.append(" ");
            return this;
        }

        public Builder setSimplePath(String path) {
            this.path = path;
            requestBuilder.append(path);
            requestBuilder.append(" HTTP/1.1");
            requestBuilder.append("\r\n");
            return this;
        }

        public Builder setPathWithVariable(String path, Object pathVariable) {
            this.path = path;
            requestBuilder.append(path);
            requestBuilder.append("/");
            requestBuilder.append(pathVariable);
            requestBuilder.append(" HTTP/1.1");
            requestBuilder.append("\r\n");
            return this;
        }

        public Builder setPathWithRequestParam(String path, Map<String, Object> requestParams) {
            this.path = path;
            requestBuilder.append(path);
            requestBuilder.append("?");
            if (!requestParams.isEmpty()) {
                int size = requestParams.size();
                for (String key:requestParams.keySet()) {
                    requestBuilder.append(key);
                    requestBuilder.append("=");
                    requestBuilder.append(requestParams.get(key));
                    size--;
                    if (size > 0) {
                        requestBuilder.append("&");
                    }
                }
            }
            requestBuilder.append(" HTTP/1.1");
            requestBuilder.append("\r\n");
            return this;
        }

        public Builder setHost(String host) {
            this.host = host;
            requestBuilder.append("Host: ");
            requestBuilder.append(host);
            requestBuilder.append("\r\n");
            return this;
        }

        public Builder setContentType(String contentType) {
            requestBuilder.append("Content-Type: ");
            requestBuilder.append(contentType);
            requestBuilder.append("\r\n");
            return this;
        }

        public Builder setRequestBody(Object requestBody) {
        this.requestBody = requestBody;

        String newRequestBody = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            newRequestBody = mapper.writeValueAsString(requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestBuilder.append("Content-Length: ");
        requestBuilder.append(newRequestBody.length());
        requestBuilder.append("\r\n");
        requestBuilder.append("\r\n");
        requestBuilder.append(newRequestBody);
        return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    private Request(Builder builder) {
        method = builder.method;
        path = builder.path;
        host = builder.host;
        requestBody = builder.requestBody;
        requestString = builder.requestBuilder;
    }

    @Override
    public String toString() {
        return requestString.toString();
    }
}