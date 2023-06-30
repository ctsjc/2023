Install Ingress Controller
```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.0/deploy/static/provider/cloud/deploy.yaml
```

Local testing


Let's create a simple web server and the associated service:

```bash
kubectl create deployment demo --image=httpd --port=80
kubectl expose deployment demo
```


Then create an ingress resource. The following example uses a host that maps to localhost:

```bash
kubectl create ingress demo-localhost --class=nginx \
  --rule="demo.localdev.me/*=demo:80"
 ```

Now, forward a local port to the ingress controller:

```bash
kubectl port-forward --namespace=ingress-nginx service/ingress-nginx-controller 8080:80
```

Test
```bash
curl --resolve demo.localdev.me:8080:127.0.0.1 http://demo.localdev.me:8080
```