package com.cp.tasky.ui.sharecomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.cp.tasky.R
import com.cp.tasky.ui.theme.MEDIUM_8_DP
import com.cp.tasky.ui.theme.SMALL_4_DP

@Composable
fun AgendaItemSummary(
    modifier: Modifier = Modifier,
    isDone: Boolean = false,
    itemDescription: String = "",
    dropdownMenuExpanded: Boolean = true,
    itemTitle: String,
    remindAt: String,
    backgroundColor: Color,
    onRadioButtonClick: (Boolean) -> Unit,
    onDropdownMenuClick: (Boolean) -> Unit,
    onItemOpenClick: () -> Unit,
    onItemEditClick: () -> Unit,
    onItemDeleteClick: () -> Unit
) {

    val constraint = ConstraintSet {
        val (radioButton, itemTitleText, itemDescriptionText, icon) = createRefsFor(
            RADIO_BUTTON,
            ITEM_TITLE_TEXT,
            ITEM_DESCRIPTION_TEXT,
            ICON
        )

        constrain(radioButton) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }

        constrain(itemTitleText) {
            start.linkTo(radioButton.end)
            top.linkTo(radioButton.top)
            bottom.linkTo(radioButton.bottom)
        }

        constrain(itemDescriptionText) {
            start.linkTo(itemTitleText.start)
            top.linkTo(itemTitleText.bottom)
        }

        constrain(icon) {
            top.linkTo(itemTitleText.top)
            bottom.linkTo(itemTitleText.bottom)
            end.linkTo(parent.end)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(MEDIUM_8_DP)
            )
    ) {
        Column {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
                constraintSet = constraint
            ) {
                RadioButton(
                    modifier = Modifier.layoutId(RADIO_BUTTON),
                    selected = isDone,
                    onClick = {
                        onRadioButtonClick(!isDone)
                    })

                Text(
                    modifier = Modifier.layoutId(ITEM_TITLE_TEXT),
                    text = itemTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textDecoration = if(isDone) TextDecoration.LineThrough else TextDecoration.None
                )

                Text(
                    text = itemDescription,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .layoutId(ITEM_DESCRIPTION_TEXT)
                        .padding(top = SMALL_4_DP)
                )

                Box(modifier = Modifier.layoutId(ICON)) {
                    Icon(
                        painter = painterResource(id = R.drawable.more_horizontal_line),
                        contentDescription = stringResource(R.string.more),
                        modifier = Modifier
                            .padding(end = MEDIUM_8_DP)
                            .clickable {
                                onDropdownMenuClick(!dropdownMenuExpanded)
                            }
                    )

                    DropdownMenu(
                        expanded = dropdownMenuExpanded,
                        onDismissRequest = { onDropdownMenuClick(false) })
                    {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.open)) },
                            onClick = { onItemOpenClick() })

                        HorizontalDivider()

                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.edit)) },
                            onClick = {
                                onItemEditClick()
                            })

                        HorizontalDivider()

                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.delete)) },
                            onClick = {
                                onItemDeleteClick()
                            })
                    }
                }
            }

            Text(
                text = remindAt,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(MEDIUM_8_DP)
            )
        }
    }
}

@Preview
@Composable
private fun AgendaItemSummaryPreview() {
    AgendaItemSummary(
        itemTitle = "Project X",
        itemDescription = "Work on it",
        remindAt = "May 5, 2024",
        backgroundColor = Color.Green,
        onRadioButtonClick = {},
        onDropdownMenuClick = {},
        onItemOpenClick = {},
        onItemEditClick = {},
        onItemDeleteClick = {}
    )
}

const val RADIO_BUTTON = "radio_button"
const val ITEM_TITLE_TEXT = "item_title_text"
const val ITEM_DESCRIPTION_TEXT = "item_description_text"
const val ICON = "icon"
