import $ivy.`com.bloxbean.cardano:cardano-client-lib:0.4.3`

import com.bloxbean.cardano.client.crypto._;
import com.bloxbean.cardano.client.address._;
import com.bloxbean.cardano.client.common.model._;
import com.bloxbean.cardano.client.crypto.bip32.key.HdPublicKey;

// ~/hydra-files$ cat alice.sk
// {
//     "type": "HydraSigningKey_ed25519",
//     "description": "",
//     "cborHex": "5820f2c820dad6d34cf8e810c367e27690b18e2267d900f7f7a4c9b8e6756ac6cb39"
// }

// amm public-key-from-private-key-as-cbor.sc 5820f2c820dad6d34cf8e810c367e27690b18e2267d900f7f7a4c9b8e6756ac6cb39 false

def getAddrFromVerificationKey(vk: VerificationKey, isMainnet: Boolean = true) = {
    val hdPublicKey = new HdPublicKey();
    hdPublicKey.setKeyData(vk.getBytes());

    val address = AddressProvider.getEntAddress(hdPublicKey, if (isMainnet) { Networks.mainnet() } else { Networks.testnet() });
    address.toBech32();
}

@main
def main(skey: String, isMainnet: Boolean = true) = {
   val secretKey = new SecretKey(skey)
   val vk: VerificationKey = KeyGenUtil.getPublicKeyFromPrivateKey(secretKey)
   val pk: String = getAddrFromVerificationKey(vk, isMainnet)

   println(s"The address for secret key: $skey is $pk")
}