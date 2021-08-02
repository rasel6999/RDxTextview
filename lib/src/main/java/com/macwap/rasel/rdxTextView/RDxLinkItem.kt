package com.macwap.rasel.rdxTextView

data class RDxLinkItem(var startPoint: Int?,
					   var endPoint: Int?,
					   val originalText: String?,
					   val transformedText: String?,
					   val parameterText: String?,
					   val valueText: String?,
					   val mode: Mode?)
