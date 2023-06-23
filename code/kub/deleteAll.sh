kubectl delete deployment msv01
kubectl delete svc msv01

kubectl delete deployment msv02
kubectl delete svc msv02

kubectl delete gateway msv02
kubectl delete virtualservice msv02

kubectl label namespace dev2023 istio-injection