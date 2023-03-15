import $ivy.`com.bloxbean.cardano:cardano-client-lib:0.4.3`

import com.bloxbean.cardano.client.crypto._;
import com.bloxbean.cardano.client.address._;
import com.bloxbean.cardano.client.common.model._;
import com.bloxbean.cardano.client.crypto.bip32.key.HdPublicKey;

// ~/hydra-files$ cat alice.vk
// {
//     "type": "HydraSigningKey_ed25519",
//     "description": "",
//     "cborHex": "5820786f023e000dcdc1860d93364ef460d0dd5ae14a91d198b2a8a52eece306985a"
// }

// amm public-key-from-private-key-as-cbor.sc 5820786f023e000dcdc1860d93364ef460d0dd5ae14a91d198b2a8a52eece306985a false

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