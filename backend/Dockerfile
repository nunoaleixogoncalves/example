FROM openjdk:11-jdk-slim

RUN groupadd -g 1000 nonroot && \
    useradd -m -u 1000 -g nonroot nonroot && \
    mkdir -p /home/nonroot/.m2 && \
    chown -R nonroot:nonroot /home/nonroot/.m2

WORKDIR /app