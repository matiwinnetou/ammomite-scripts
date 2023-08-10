import $ivy.`com.bloxbean.cardano:cardano-client-lib:0.5.0-alpha.4`

import com.bloxbean.cardano.client.crypto._;
import com.bloxbean.cardano.client.address._;
import com.bloxbean.cardano.client.account._;
import com.bloxbean.cardano.client.common.model._;
import com.bloxbean.cardano.client.crypto.bip32.key.HdPublicKey;

import com.bloxbean.cardano.client.crypto.KeyGenUtil;

// amm cardano/generate-accounts.sc --isMainnet true --numberAccounts 100

@main
def main(isMainnet: Boolean = true, numberAccounts: Int) = {
    val network = if (isMainnet) { Networks.mainnet() } else { Networks.testnet() }

    var accounts = scala.collection.mutable.HashSet[Account]();
    for (i <- 1 to numberAccounts) {
        val newAcc = new Account(network);

        accounts.add(newAcc);
    }

    println(s"The mnemonic count: ${accounts.size}")

    val it = accounts.iterator

    while (it.hasNext) {
        val a = it.next
        println(s"${a};${a.mnemonic()}")
    }

}