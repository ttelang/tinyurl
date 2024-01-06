import random

def encode_url(chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-"):
    max_id = 64**6  # Adjust the range based on your requirement
    unique_id = random.randint(0, max_id)

    short_url = []
    while unique_id > 0:
        unique_id, remainder = divmod(unique_id, 64)
        short_url.append(chars[remainder])
    return ''.join(reversed(short_url))

# Example usage
short_url = encode_url()
print(f"Short URL: {short_url}")
