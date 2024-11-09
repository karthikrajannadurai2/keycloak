WebAuthn, or Web Authentication, is a web standard created by the World Wide Web Consortium (W3C) and the FIDO Alliance to enable secure, passwordless authentication on websites and apps. Instead of relying on passwords, which are often vulnerable to phishing and other attacks, WebAuthn uses public-key cryptography to authenticate users based on something they *have* (like a smartphone or security key) and often something they *are* (such as a fingerprint or face scan).

### How WebAuthn Works
1. **Registration**: During signup, a user registers a device (like a smartphone or security key) with the website. The device generates a pair of cryptographic keys: a public key, which the website stores, and a private key, which remains securely on the device.

2. **Authentication**: When the user wants to log in, the website sends a challenge to the device. The device uses its private key to sign this challenge, verifying the user’s identity. The signed challenge is sent back to the website, which uses the public key to validate the response.

Because the private key never leaves the user’s device, WebAuthn is resistant to common attacks, such as phishing and credential stuffing. This makes it a strong option for secure, user-friendly authentication.