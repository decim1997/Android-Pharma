package com.example.thony.pharma


import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_qrcode_reader.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QRCodeReaderFragment : Fragment()
{

    var code: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.fragment_qrcode_reader, container, false)
        var pharmaqrcode = view.findViewById<ImageView>(R.id.qrcodeimg)

        println("Codeflkdvkvmmsvlcc"+code)

        val generatecode = code!!.trim()

        if(generatecode != null)
        {
            var multiwriter:MultiFormatWriter = MultiFormatWriter()
            var matrix:BitMatrix = multiwriter.encode(generatecode, BarcodeFormat.QR_CODE,500,500)
            var barencoder:BarcodeEncoder = BarcodeEncoder()
             var bitmap = barencoder.createBitmap(matrix)
            pharmaqrcode.setImageBitmap(bitmap)
        }

        return  view
    }


}
