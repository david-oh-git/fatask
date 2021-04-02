/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.payment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.davidosemwota.payment.R
import io.davidosemwota.payment.databinding.FragmentHomeBinding
import io.davidosemwota.utility.extentions.currencyFormat
import io.davidosemwota.utility.extentions.observe
import io.davidosemwota.utility.extentions.sendLongSnack
import io.davidosemwota.utility.extentions.sendSnack
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val card: Card = Card(
        "507850785078507812",
        4,
        22,
        "081"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.apply {
            cardNumber.text = card.number
            cardPin.text = getString(R.string.fake_card_pin)
            cardCvv.text = card.cvc
            email.text = getString(R.string.test_email)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.charge.setOnClickListener { chargeCard() }
        observe(viewModel.accountBalance, ::setAmount)
    }

    private fun setAmount(amount: Double) {
        binding.balance.text = "\$$amount"
    }

    private fun chargeCard() {

        if (card.isValid) {
            val amount: Int = binding.getAmount.text.toString().toInt()
            val charge = Charge()
            charge.card = card
            charge.amount = amount
            charge.email = "hirakoshinji722@gmail.com"

            PaystackSdk.chargeCard(
                requireActivity(),
                charge,
                object : Paystack.TransactionCallback {
                    override fun onSuccess(transaction: Transaction?) {
                        sendLongSnack(
                            binding.container,
                            "$amount has been deducted"
                        )

                        createSuccessDialog(amount)
                        viewModel.setBalance(amount / 485.0)
                        binding.getAmount.setText("")
                    }

                    override fun beforeValidate(transaction: Transaction?) {
                        TODO("Not yet implemented")
                    }

                    override fun onError(error: Throwable?, transaction: Transaction?) {
                        Timber.d("Error msg is ${error?.message}")
                        sendSnack(
                            binding.container,
                            "Transaction failed"
                        )
                    }
                }
            )
        }
    }

    private fun createSuccessDialog(amount: Int) {
        val dollarAmount = (amount / 485.0).currencyFormat
        MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialogStyle)
            .setTitle("Successful Transaction")
            .setNeutralButton("Dismiss") { dialog, _ ->
                dialog.dismiss()
            }.setMessage("\$$dollarAmount has been sent to your account.")
            .show()
    }
}
