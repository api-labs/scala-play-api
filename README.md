# scala-play-api

### Install sbt
`https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Mac.html`

### Build docker image
```
cd scala-play-api
sbt docker:publishLocal
```

### Install service in Kubernetes
`helm install --name scala-play-api charts/scala-play-api --set image.tag=1.0.0 -f charts/scala-play-api/values.yaml`

You can run inside the container to test that the service is working:
`curl "localhost:9000/echo?id=1&payload=test&time_sent=122347234234&sleep_intv=0"`

If you want to hit the service outside the cluster, you need to expose port 9000.
