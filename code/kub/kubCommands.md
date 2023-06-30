

<details>
<summary></summary>
</details>


<details>

<summary>Create Docker Image</summary>

```bash
docker build -t iamjc321/msv01:latest . 
docker login -u iamjc321 docker.io
docker push iamjc321/msv01:latest
```

</details>


<details>
<summary>Install MiniKube on Machine</summary>
Minikube is kubernet software like Rancher.

[MiniKube Installation](https://minikube.sigs.k8s.io/docs/start/)
</details>

<details>
<summary>Foundations</summary>

Create Namespace
```bash 
kubectl create namespace NAME
```

Set Automatic Istio configurations to namespace
```bash
kubectl label namespace dev2023 istio-injection=enabled
```

</details>



<details>
<summary>Create</summary>


Run All
```bash
kubectl apply -f msv01.yaml -f msv02.yaml -f istio.yaml
```

Create a pod with curl and make a request to other pods from it
```bash
kubectl run curl-j --image=radial/busyboxplus:curl -i --tty --rm
```
</details>

<details>
<summary>Delete</summary>

```bash 
kubectl delete deployment msv01 -n dev2023
```
```bash 
kubectl delete svc msv01 -n dev2023
```
```bash 
kubectl delete deployment msv02 -n dev2023
```
```bash 
kubectl delete svc msv02 -n dev2023
```
```bash 
kubectl delete gateway msv02
```
```bash 
kubectl delete virtualservice msv02
```

```bash 
kubectl label namespace dev2023 istio-injection
```

</details>




<details>
<summary>Get</summary>

Check services in namespace
```bash
kubectl get svc -n dev2023
```

Find IP of gateway 

```bash

```

Check Istio
```bash
istioctl analyze -n dev2023
```

Use the following command to access the Kiali dashboard:

```bash
istioctl dashboard kiali
```

Get logs
```bash
kubectl logs deployment/<name-of-deployment> # logs of deployment
kubectl logs -f deployment/msv01 -n dev2023
```

</details>


<details>
<summary>Operations</summary>

Run commands in running pod
```bash
kubectl exec --stdin --tty msv01-5cfb69ffdb-wqchx -n dev2023 -- ls /
kubectl exec --stdin --tty msv02-6cc57f9f78-hqh9h -n dev2023 -- wget localhost:8081/portfolio/
```

Run Curl
```bash
kubectl run curl-j --image=radial/busyboxplus:curl -i --tty --rm
```

Make a call from ingress 

```bash
#Get IP Address of Gateway
kubectl get svc istio-ingressgateway -n istio-system

#Use IP to make call to internal service, <gateway-ip>:<gateway-port>/<kub-service>/<endpoint>
curl http://10.43.108.251:80/search/iscall
```

</details>



<details>
<summary>Example</summary>

<details>
<summary>Spring Service</summary>

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: msv01
  namespace: dev2023
spec:
  replicas: 1
  selector:
    matchLabels:
      app: msv01
  template:
    metadata:
      labels:
        app: msv01
    spec:
      containers:
        - name: msv01
          image: iamjc321/msv01:1.0.4
          imagePullPolicy: Always
          ports:
            - containerPort: 8081
          env:
            - name: PORTFOLIO_URL
              value: http://msv02:8081/portfolio
---
apiVersion: v1
kind: Service
metadata:
  name: msv01
  namespace: dev2023
spec: 
  ports:
  - port: 8082
    targetPort: 8082
    protocol: TCP
    name: http
  selector:
    app: msv01
```
</details>

</details>