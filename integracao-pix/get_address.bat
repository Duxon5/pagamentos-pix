container_name="pix"  # Capture container name as first argument

# Get container ID based on name
container_id=$(docker inspect -f '{{.Id}}' "$container_name")

# Check if container exists
if [[ -z "$container_id" ]]; then
  echo "Error: Container '$container_name' not found"
  exit 1
fi

# Get IP address using the container ID
ip_address=$(docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' "$container_id")

echo "IP address of container '$container_name': $ip_address"

pause