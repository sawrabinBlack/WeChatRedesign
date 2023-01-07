package com.rabin.wechat.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.rabin.wechat.R
import kotlinx.android.synthetic.main.dialog_qr_fragment.*

class QRDialogFragment : DialogFragment() {
    companion object {
       const val TAG_EDIT_QR_DIALOG = "TAG_EDIT_QR_DIALOG"
        const val BUNDLE_USER_ID = "BUNDLE_USER_ID"
        fun newQRdialog() : QRDialogFragment{
            return QRDialogFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = arguments?.getString(BUNDLE_USER_ID)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap =
            barcodeEncoder.encodeBitmap(userId, BarcodeFormat.QR_CODE, 200, 200)
        ivQRCode.setImageBitmap(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_qr_fragment, container, false)
        return view
    }
}