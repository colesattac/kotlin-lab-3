package com.colesattac.scrolllist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.colesattac.scrolllist.Fact
import com.colesattac.scrolllist.FactsRepository
import com.colesattac.scrolllist.ui.theme.ScrollListTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrollListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FactsList(
                        facts = FactsRepository.facts,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun FactCard(fact: Fact, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date()),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = stringResource(id = fact.titleRes),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Image(
                painter = painterResource(id = fact.imageRes),
                contentDescription = stringResource(id = fact.titleRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = fact.descriptionRes),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun FactsList(facts: List<Fact>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(facts) { fact ->
            FactCard(
                fact = fact,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FactCardPreview() {
    ScrollListTheme {
        FactCard(fact = FactsRepository.facts[0])
    }
}
